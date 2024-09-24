import React from 'react'
import Mainlogo from '../images/mainlogo.png';
import { useTranslation } from 'react-i18next';
import './heading.css';

const Heading = () => {
    const { t, i18n } = useTranslation();
    return (
        <div>
            <div class="own-heading container-fluid bg-primary p-5 text-white text-center">
                <img src={ Mainlogo} alt="main logo" width="170px"  height="170px"/>
                <h3>{t('heading_title')}</h3>
                <h5>{t('heading_text')}</h5>
                <h6>{t('heading_address')}</h6>
            </div>
        </div>
    )
}

export default Heading