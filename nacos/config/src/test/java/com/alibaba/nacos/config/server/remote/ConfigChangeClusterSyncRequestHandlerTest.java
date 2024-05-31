/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.config.server.remote;

import com.alibaba.nacos.api.config.remote.request.cluster.ConfigChangeClusterSyncRequest;
import com.alibaba.nacos.api.config.remote.response.cluster.ConfigChangeClusterSyncResponse;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.api.remote.response.ResponseCode;
import com.alibaba.nacos.config.server.service.dump.DumpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ConfigChangeClusterSyncRequestHandlerTest {
    
    private ConfigChangeClusterSyncRequestHandler configChangeClusterSyncRequestHandler;
    
    @Mock
    private DumpService dumpService;
    
    @BeforeEach
    void setUp() throws IOException {
        configChangeClusterSyncRequestHandler = new ConfigChangeClusterSyncRequestHandler(dumpService);
    }
    
    @Test
    void testHandle() throws NacosException {
        ConfigChangeClusterSyncRequest configChangeSyncRequest = new ConfigChangeClusterSyncRequest();
        configChangeSyncRequest.setRequestId("");
        configChangeSyncRequest.setDataId("dataId");
        configChangeSyncRequest.setTag("tag");
        configChangeSyncRequest.setLastModified(1L);
        configChangeSyncRequest.setBeta(false);
        RequestMeta meta = new RequestMeta();
        meta.setClientIp("1.1.1.1");
        ConfigChangeClusterSyncResponse configChangeClusterSyncResponse = configChangeClusterSyncRequestHandler.handle(
                configChangeSyncRequest, meta);
        assertEquals(configChangeClusterSyncResponse.getResultCode(), ResponseCode.SUCCESS.getCode());
    }
}