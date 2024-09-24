import axios from 'axios';


const API_URL = 'https:/toshdosazamat-polimerlar-backend-ab3e.twc1.net/api';

// const API_URL = 'http://localhost:8080/api';



export const getNews = async (lang = 'uz') => {
    try {
        const response = await axios.get(`${API_URL}/news/get/all`, {
            params: { lang }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching news:', error);
        throw error;
    }
}