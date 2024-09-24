import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import globalUz from './translation/uz/global.json';
import globalEn from './translation/en/global.json';
import globalRu from './translation/ru/global.json';

const resources = {
  uz: {
    translation: globalUz
  },
  en: {
    translation: globalEn
  },
  ru: {
    translation: globalRu
  }
};

const savedLanguage = localStorage.getItem('language') || 'uz';

i18n
  .use(initReactI18next) 
  .init({
    resources,
    lng: savedLanguage, 
    fallbackLng: 'uz',
    interpolation: {
      escapeValue: false 
    }
  });

export default i18n;
