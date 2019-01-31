/*
 *       Copyright© (2019) WeBank Co., Ltd.
 *
 *       This file is part of weidentity-sample.
 *
 *       weidentity-sample is free software: you can redistribute it and/or modify
 *       it under the terms of the GNU Lesser General Public License as published by
 *       the Free Software Foundation, either version 3 of the License, or
 *       (at your option) any later version.
 *
 *       weidentity-sample is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *       GNU Lesser General Public License for more details.
 *
 *       You should have received a copy of the GNU Lesser General Public License
 *       along with weidentity-sample.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.webank.weid.demo.command;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webank.weid.demo.common.util.FileUtil;
import com.webank.weid.protocol.base.Credential;

/**
 * the DemoUtil for command.
 *
 */
public class DemoUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(DemoUtil.class);
    
    private static final String TEMP_DIR = "./tmp/";
    
    private static final String CRED_FILE = TEMP_DIR + "credential.json";
    
    /**
     * temporary data file.
     */
    private static final String TEMP_FILE = TEMP_DIR + "temp.data";
    
    /**
     * read credential.
     * @return
     */
    public static Credential getCredentialFromJson() {
        
        Credential credential = null;
        String jsonStr = FileUtil.getDataByPath(CRED_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            credential = objectMapper.readValue(jsonStr, Credential.class);
        } catch (IOException e) {
            logger.error("getCredentialFromJson error", e);
        }
        return credential;
    }
    
    /**
     * get temporary data from file.
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getTempDataFromFile() {
        
        // getting authority data from temporary file.
        String json = FileUtil.getDataByPath(DemoUtil.TEMP_FILE);
        ObjectMapper om = new ObjectMapper();
        Map<String, String> paramMap = null;
        try {
            paramMap = om.readValue(json, Map.class);
        } catch (IOException e) {
            logger.error("read temp.data error", e);
        }
        return paramMap;
    }

    /**
     * save credential.
     * @param credential require
     */
    public static String saveCredential(Credential credential) {
        
        String dataStr = FileUtil.formatObjectToString(credential);
        FileUtil.checkDir(TEMP_DIR);
        return FileUtil.saveFile(CRED_FILE, dataStr);
        
    }
    
    /**
     * save temporary data.
     * @param map require
     */
    public static void saveTemData(Map<String, String> map) {
        
        String dataStr = FileUtil.formatObjectToString(map);
        FileUtil.checkDir(TEMP_DIR);
        FileUtil.saveFile(TEMP_FILE, dataStr);
    } 
}