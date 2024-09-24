import axios from 'axios';

const API_URL = 'https:/toshdosazamat-polimerlar-backend-ab3e.twc1.net/api';
// const API_URL = 'http://localhost:8080/api';



export const getCouncils = async (lang = 'uz') => {
    try {
        const response = await axios.get(`${API_URL}/council/get/all`, {
            params: { lang }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching labs:', error);
        throw error;
    }
};
export const getCouncil = async (councilId, lang = 'uz') => {
    try {
        const response = await axios.get(`${API_URL}/council/get/${councilId}`, {
            params: { lang }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching council:', error);
        throw error;
    }
};
export const getStatements = async (councilId, lang = 'uz') => {
    try {
        const response = await axios.get(`${API_URL}/statement/get/all/${councilId}`, {
            params: { lang }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching statements:', error);
        throw error;
    }
};