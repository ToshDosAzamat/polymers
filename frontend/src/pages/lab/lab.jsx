import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { useParams } from 'react-router-dom';
import Heading from '../../component/heading/heading';
import Navbar from '../../component/navbar/navbar';
import Footer from '../../component/footer/footer';
import { getLab } from '../../service/apiLab';
import { getLabs } from '../../service/apiLab';
import { getDevices } from '../../service/apiLab';
import './lab.css';
import Insititut from '../../component/images/insititut.png';

const extractBase64Image = (imageObject) => {
    return `data:image/jpeg;base64,${imageObject}`;
};

const Lab = () => {
    const { t, i18n } = useTranslation();
    const { labId } = useParams();
    const [lab, setLab] = useState(null);
    const [labs, setLabs] = useState([]);
    const [devices, setDevices] = useState([]);


    useEffect(() => {
        const fetchLab = async () => {
            try {
                const lab = await getLab(labId, i18n.language);
                console.log('API response:', lab);

                const labWithBase64Image = {
                    ...lab,
                    image: extractBase64Image(lab.image.img)
                };

                console.log('Processed Lab:', labWithBase64Image);
                setLab(labWithBase64Image);
            } catch (error) {
                console.error('Error fetching Lab:', error);
            }
        };
        fetchLab();
    }, [i18n.language, labId]);

    useEffect(() => {
        const fetchLabs = async () => {
            try {
                const labs = await getLabs(i18n.language);
                setLabs(labs);
            } catch (error) {
                console.error('Error fetching labs:', error);
            }
        };
        fetchLabs();
    }, [i18n.language]);

    useEffect(() => {
        const fetchDevices = async () => {
          try {
            const devices = await getDevices(labId, i18n.language);
            console.log('API response:', devices);
    
            const devicesWithBase64Images = devices.map(device => ({
              ...device,
              image: extractBase64Image(device.image.img)
            }));
    
            console.log('Processed devices:', devicesWithBase64Images);
            setDevices(devicesWithBase64Images);
          } catch (error) {
            console.error('Error fetching devices:', error);
          }
        };
        fetchDevices();
      }, [i18n.language, labId]);

    return (
        <div>
            <Heading />
            <Navbar />
            <div class="container-fluid p-lg-5 text-white text-center heading-name-own">
                <h3>{lab && (lab.name)}</h3>
            </div>
            <div class="container mt-5">
                <div class="row">
                    <div class="col-sm-4">
                        <h2>{t('lab_left_title')}</h2>
                        <hr />
                        <img src={Insititut} class="lab-img" alt="Insititutimiz" />
                        <p class="pl-5 text-decoration-p text-indent mt-3">{t('lab_left_text')}</p>
                        <h3 class="mt-4">{t('lab_left_labs')}</h3>
                        <hr />
                        <ul class="nav nav-pills flex-column">
                            {labs.map((lab) => (
                                <li class="nav-item" key={lab.lab.id}>
                                    <a class="nav-link" href={`/lab/${lab.lab.id}`}>
                                        {lab.name}
                                    </a>
                                </li>
                            ))}
                        </ul>
                        <hr class="d-sm-none" />
                    </div>
                    <div class="col-sm-8">
                        {lab && (
                            <div>
                                <h2>{t('lab_right_title')}</h2>
                                <hr />
                                <img src={lab.image} class="lab-img" alt={lab.name} />
                                <p class="mt-4 fs-4"><b>{lab.name}</b></p>
                                <hr />
                                <p class="text-decoration-p">{lab.description}</p>
                            </div>)
                        }
                    </div>
                </div>
            </div>

            <div class="container text-center mt-5 mb-5">
                <h2>{t('lab_device_title')}</h2>
                <hr />
                <div class="row mt-5 text-center justify-content-center">
                    {devices.map((device) => (
                        <div class="card device-card ms-3 mb-3">
                            <img src={device.image} class="card-img-top device-card-img-top" alt={device.name}/>
                            <div class="card-body">
                                <h5 class="card-title"><b>{device.name}</b></h5>
                                <hr/>
                                <p class="card-text mb-3">{device.description}</p>
                            </div>
                        </div>
                    ))}

                </div>
            </div>
            <Footer />
        </div>
    );
}

export default Lab;
