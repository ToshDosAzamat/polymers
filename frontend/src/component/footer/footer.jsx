import React, { useState } from 'react';
import axios from 'axios';
import Send from '../images/send.png';
import { useTranslation } from 'react-i18next';
import './footer.css';

const Footer = () => {
  const { t } = useTranslation();
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  const validateEmail = (email) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
  };

  const handleInputChange = (e) => {
    setEmail(e.target.value);
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    setMessage('');

    if (!validateEmail(email)) {
      setMessage(t('invalid_email'));
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/member/create', null, {
        params: { email },
      });

      setMessage(t('subscription_success'));
      setEmail('');
    } catch (error) {
      console.error('There was an error creating the member!', error);
      setMessage(t('subscription_failed'));
    }
  };

  return (
    <div>
      <div className="container-fluid bg-primary text-white footer-container mt-5 pt-5">
        <div className="container text-white">
          <footer className="py-4">
            <div className="row justify-content-center">
              <div className="col-6 col-md-2 mb-3">
                <h5>{t('home')}</h5>
                <hr />
                <ul className="nav flex-column">
                  <li className="nav-item mb-2"><a href="/" className="nav-link p-0 text-white">{t('home')}</a></li>
                  <li className="nav-item mb-2"><a href="/news" className="nav-link p-0 text-white">{t('news')}</a></li>
                  <li className="nav-item mb-2"><a href="/services" className="nav-link p-0 text-white">{t('service')}</a></li>
                  <li className="nav-item mb-2"><a href="/contact" className="nav-link p-0 text-white">{t('contact')}</a></li>
                </ul>
              </div>

              <div className="col-6 col-md-2 mb-3">
                <h5>{t('councils')}</h5>
                <hr />
                {/* 
                <ul className="nav flex-column">
                  <li th:each="council: ${councils}" className="nav-item mb-2"><a th:href="@{'/council/'+${council.council.id}}" className="nav-link p-0 text-white" th:text="${council.name}"></a></li>
                </ul> 
                */}
              </div>

              <div className="col-md-5 offset-md-1 mb-3">
                <form onSubmit={handleFormSubmit}>
                  <h5>{t('footer_subscribe_title')}</h5>
                  <hr />
                  <p>{t('footer_subscribe_text')}</p>
                  <div className="d-flex flex-column flex-sm-row w-100 gap-2">
                    <label htmlFor="newsletter1" className="visually-hidden">Email</label>
                    <input
                      id="newsletter1"
                      type="text"
                      className="form-control"
                      placeholder="email_example@mail.uz"
                      value={email}
                      onChange={handleInputChange}
                      required
                    />
                    <button className="btn btn-light" type="submit">
                      <img src={Send} alt="send icon" width="30px" height="30px" />
                    </button>
                  </div>
                  {message && <p>{message}</p>}
                </form>
              </div>
            </div>

            <div className="d-flex flex-column flex-sm-row justify-content-center py-4 my-4 border-top">
              <p>{t('heading_title')}</p>
              <a href="https://github.com/ToshDosAzamat" className="ms-3">Developer</a>
            </div>
          </footer>
        </div>
      </div>
    </div>
  );
};

export default Footer;
