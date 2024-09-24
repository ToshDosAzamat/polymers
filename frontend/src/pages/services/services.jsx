import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import Heading from '../../component/heading/heading';
import Navbar from '../../component/navbar/navbar';
import Footer from '../../component/footer/footer';
import { getOwnServices } from '../../service/apiService';
import './services.css';
import TypeOne from '../../component/images/typeone.png';
import TypeTwo from '../../component/images/typetwo.png';
import TypeThree from '../../component/images/typethree.png';

const extractBase64Image = (imageObject) => {
    return `data:image/jpeg;base64,${imageObject}`;
};

const Services = () => {
    const { t, i18n } = useTranslation();
    const [ownServices, setOwnServices] = useState([]);

    useEffect(() => {
        const fetchOwnServices = async () => {
            try {
                const ownServices = await getOwnServices(i18n.language);
                console.log('API response:', ownServices);

                const ownServicesWithBase64Images = ownServices.map(ownService => ({
                    ...ownService,
                    image: extractBase64Image(ownService.image.img)
                }));

                console.log('Processed own services:', ownServicesWithBase64Images);
                setOwnServices(ownServicesWithBase64Images);
            } catch (error) {
                console.error('Error fetching Own Services:', error);
            }
        };
        fetchOwnServices();
    }, [i18n.language]);

    return (
        <div>
            <Heading />
            <Navbar />
            <div className="container-fluid p-lg-5 text-white text-center heading-name-own justify-content-center align-items-center">
                <h3>{t('service_title')}</h3>
            </div>
            <div className="container text-center">
                <h3 className='mt-5'>{t('service_type_title')}</h3>
                <hr className='mt-5 mb-5' />
                <div className="card-group">
                    <div className="card">
                        <img src={TypeOne} className="card-img-top own-card-img-top" alt="..." />
                        <div className="card-body">
                            <hr className='mt-2 mb-2' />
                            <p className="card-text own-card-text">{t('type_research_one')}</p>
                            <hr className='mt-2 mb-2' />
                        </div>
                    </div>
                    <div className="card">
                        <img src={TypeTwo} className="card-img-top own-card-img-top" alt="..." />
                        <div className="card-body">
                            <hr className='mt-2 mb-2' />
                            <p className="card-text own-card-text">{t('type_research_two')}</p>
                            <hr className='mt-2 mb-2' />
                        </div>
                    </div>
                    <div className="card">
                        <img src={TypeThree} className="card-img-top own-card-img-top" alt="..." />
                        <div className="card-body">
                            <hr className='mt-2 mb-2' />
                            <p className="card-text own-card-text">{t('type_research_three')}</p>
                            <hr className='mt-2 mb-2' />
                        </div>
                    </div>
                </div>

                <div className="container text-center mt-5 mb-5">
                    <div className="row mt-5 text-center justify-content-center text-center">
                        {ownServices.map((service) => (
                            <div key={service.id} className='ser-card d-flex'>
                                <div className="ser-img">
                                    <img src={service.image} className='ser-img-img' alt={service.name} />
                                </div>
                                <div className="ser-desc justify-content-center text-center">
                                    <p className="fw-bold">{service.name}</p>
                                    <hr/>
                                    <p className="text-ser">{service.description}</p>
                                </div>
                            </div> 
                        ))}
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
};

export default Services;
