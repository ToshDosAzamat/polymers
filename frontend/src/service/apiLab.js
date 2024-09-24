import axios from 'axios';

const API_URL = 'https://toshdosazamat-polimerlar-backend-ab3e.twc1.net/api';
// const API_URL = 'http://localhost:8080/api';

export const getLabs = async (lang = 'uz') => {
    try {
        const response = await axios.get(`${API_URL}/lab/get/all`, {
            params: { lang }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching labs:', error);
        throw error;
    }
};
export const getLab = async (labId, lang = 'uz') => {
    try {
        const response = await axios.get(`${API_URL}/lab/get/${labId}`, {
            params: { lang }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching labs:', error);
        throw error;
    }
};
export const getDevices = async (labId, lang = 'uz') => {
    try {
        const response = await axios.get(`${API_URL}/device/get/all/${labId}`, {
            params: { lang }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching devices:', error);
        throw error;
    }
};

