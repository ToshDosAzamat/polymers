import axios from 'axios';

const API_URL = 'https:/toshdosazamat-polimerlar-backend-ab3e.twc1.net/api';
// const API_URL = 'http://localhost:8080/api';



export const getOwnServices = async (lang = 'uz') => {
    try {
        const response = await axios.get(`${API_URL}/ownservice/get/all`, {
            params: { lang }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching Own service:', error);
        throw error;
    }
};