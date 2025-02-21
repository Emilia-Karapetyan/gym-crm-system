package com.gymcrm.system.config;

import com.gymcrm.system.dataloader.DataLoader;
import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.persistance.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomBeanPostProcessor<T extends BaseEntity> implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private final Map<StorageLoaderType, DataLoader<T>> dataLoaderMap = new EnumMap<>(StorageLoaderType.class);

    @Autowired
    public CustomBeanPostProcessor(List<DataLoader<T>> dataLoaders) {
        dataLoaders.forEach(loader -> dataLoaderMap.put(loader.getLoaderType(), loader));
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Optional.ofNullable(dataLoaderMap.get(StorageLoaderType.getByBeanName(beanName)))
                .ifPresent(tDataLoader -> {
                    LOGGER.debug("Found DataLoader for bean: {}, executing loadData()", beanName);
                    tDataLoader.loadData((Map<Long, T>) bean);
                });

        return bean;
    }
}
