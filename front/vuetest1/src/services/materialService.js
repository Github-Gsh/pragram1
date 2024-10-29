// src/services/materialService.js
import axios from 'axios';

const API_URL = 'http://localhost:8086/api/materials'; // 后端 API 地址

export const getMaterials = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data; // 返回数据
    } catch (error) {
        console.error('Error fetching materials:', error);
        throw error;
    }
};
// 根据 upSite 查询数据
export const getMaterialsByUpSite = (upSite) => {
    return axios.get('http://localhost:8086/api/search', {  // 保持与后端一致的路径
      params: { upSite: upSite }
    }).catch(error => {
      console.error("Error fetching materials by upSite:", error);
      throw error;
    });
  };
  