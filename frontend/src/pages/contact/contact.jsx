import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import Heading from '../../component/heading/heading';
import Navbar from '../../component/navbar/navbar';
import Footer from '../../component/footer/footer';
import './contact.css';

const Contact = () => {
    const { t } = useTranslation();
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');

    const handleEmailChange = (e) => setEmail(e.target.value);
    const handleMessageChange = (e) => setMessage(e.target.value);

    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle form submission, e.g., send the message via email or save it
        console.log('Email:', email);
        console.log('Message:', message);
        // Reset the form
        setEmail('');
        setMessage('');
    };

    return (
        <div>
            <Heading/>
            <Navbar/>

            <div className="container-fluid p-lg-5 text-white text-center heading-name-own">
                <h3>{t('contact_heading_name')}</h3>
            </div>
            <div className="container mt-5 mb-5">
                <div className="row mt-5">
                    <div className="col-6">
                        <iframe
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2996.3384642473384!2d69.25795731173721!3d41.32325289985099!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x38ae8b6b25ddfe0f%3A0x4e6b8ac6eb283e3f!2sO&#39;zR%20FA%20Polimerlar%20kimyosi%20va%20fizikasi%20instituti!5e0!3m2!1sru!2s!4v1716825787051!5m2!1sru!2s"
                            width="600"
                            height="450"
                            style={{ border: 0 }}
                            allowFullScreen=""
                            loading="lazy"
                            referrerPolicy="no-referrer-when-downgrade"
                        ></iframe>
                    </div>
                    <div className="col-6">
                        <h3 className="mt-5"><b>{t('your_send_message')}</b></h3>
                        <hr />
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="email" className="form-label">{t('your_email')}</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="email"
                                    value={email}
                                    onChange={handleEmailChange}
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="message" className="form-label">{t('your_message')}</label>
                                <textarea
                                    className="form-control"
                                    id="message"
                                    rows="5"
                                    value={message}
                                    onChange={handleMessageChange}
                                    required
                                ></textarea>
                            </div>
                            <button type="submit" className="btn btn-primary">Send</button>
                        </form>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    );
}

export default Contact;
