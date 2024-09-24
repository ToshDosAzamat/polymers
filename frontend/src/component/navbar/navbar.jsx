// Navbar.js
import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';
import './navbar.css';
import { getLabs } from '../../service/apiLab';
import { getCouncils } from '../../service/apiCouncil';

const Navbar = () => {
  const { t, i18n } = useTranslation();
  const [labs, setLabs] = useState([]);
  const [councils, setCouncils] = useState([]);
  const [language, setLanguage] = useState(localStorage.getItem('language') || i18n.language);

  const changeLanguage = (lng) => {
    i18n.changeLanguage(lng);
    setLanguage(lng);
    localStorage.setItem('language', lng);
  };

  const handleLanguageChange = (e) => {
    changeLanguage(e.target.value);
  };

  useEffect(() => {
    const fetchLabs = async () => {
      try {
        const labs = await getLabs(language);
        setLabs(labs);
      } catch (error) {
        console.error('Error fetching labs:', error);
      }
    };
    fetchLabs();
  }, [language]);

  useEffect(() => {
    const fetchCouncils = async () => {
      try {
        const councils = await getCouncils(language);
        setCouncils(councils);
      } catch (error) {
        console.error('Error fetching councils:', error);
      }
    };
    fetchCouncils();
  }, [language]);

  return (
    <div>
      <nav className="navbar navbar-expand-sm bg-light justify-content-center">
        <button className="navbar-toggler mb-1" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon float-end"></span>
        </button>
        <div className="collapse navbar-collapse justify-content-center align-item-center" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link" to="/">{t('home')}</Link>
            </li>
            <li className="nav-item dropdown own-dropdown">
              <button className="nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">{t('labs')}</button>
              <ul className="dropdown-menu own-dropdown-menu">
                {labs.map((lab) => (
                  <li key={lab.id}>
                    <Link className="dropdown-item own-dropdown-item" to={`/lab/${lab.lab.id}`}>
                      {lab.name}
                    </Link>
                  </li>
                ))}
              </ul>
            </li>
            <li className="nav-item dropdown">
              <button className="nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">{t('councils')}</button>
              <ul className="dropdown-menu">
                {councils.map((cou) => (
                  <li key={cou.id}>
                    <Link className="dropdown-item own-dropdown-item" to={`/council/${cou.id}`}>
                      {cou.name}
                    </Link>
                  </li>
                ))}
              </ul>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/news">{t('news')}</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/service">{t('service')}</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/contact">{t('contact')}</Link>
            </li>
            <div className="lang-select">
              <select className="lang-select-item" onChange={handleLanguageChange} value={language}>
                <option value="uz">UZ</option>
                <option value="en">EN</option>
                <option value="ru">RU</option>
              </select>
            </div>
          </ul>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
