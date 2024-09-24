import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import './home.css';
import YouTube from 'react-youtube';
import Heading from '../../component/heading/heading';
import Navbar from '../../component/navbar/navbar';
import Footer from '../../component/footer/footer';
import Heading1 from '../../component/images/heading1.png';
import Heading2 from '../../component/images/heading2.png';
import Heading3 from '../../component/images/heading3.png';
import { getAdministration } from '../../service/apiAdministration';
import { getCouncils } from '../../service/apiCouncil';

// Utility function to extract base64 image from the API response
const extractBase64Image = (imageObject) => {
  return `data:image/jpeg;base64,${imageObject}`;
};

const Home = () => {
  const { t, i18n } = useTranslation();
  const [isPlaying, setIsPlaying] = useState(false);
  const [administration, setAdministration] = useState([]);
  

  useEffect(() => {
    const fetchAdministrations = async () => {
      try {
        const administrations = await getAdministration(i18n.language);
        console.log('API response:', administrations);

        const administrationsWithBase64Images = administrations.map(admin => ({
          ...admin,
          image: extractBase64Image(admin.image.img)
        }));

        console.log('Processed administrations:', administrationsWithBase64Images);
        setAdministration(administrationsWithBase64Images);
      } catch (error) {
        console.error('Error fetching administrations:', error);
      }
    };
    fetchAdministrations();
  }, [i18n.language]);

  

  const onPlayerReady = (event) => {
    event.target.pauseVideo();
  };

  const handlePlayButtonClick = () => {
    setIsPlaying(true);
  };

  return (
    <div>
      <Heading />
      <Navbar />
      <div id="carouselExample" class="carousel slide">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="slider-one">
              <div class="slider-text text-center">
                <h2>{t('slider_one_title')}</h2>
                <p class="mb-4">{t('slider_one_text')}</p>
                <a href="#marketing" class="slider-button mt-5">{t('button')}</a>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <div class="slider-two">
              <div class="slider-text text-center">
                <h2>{t('slider_two_title')}</h2>
                <p class="mb-4">{t('slider_two_text')}</p>
                <a href="#marketing" class="slider-button mt-5">{t('button')}</a>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <div class="slider-three">
              <div class="slider-text text-center">
                <h2>{t('slider_three_title')}</h2>
                <p class="mb-4">{t('slider_three_text')}</p>
                <a href="#marketing" class="slider-button mt-5">{t('button')}</a>
              </div>
            </div>
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>

      <div class="container marketing mt-5 pt-5" id="marketing">
        <div class="row">
          <div class="col-lg-4 text-center heading-col">
            <img src={Heading1} class="rounded-circle-own" alt="Heading One" />
            <h2 class="fw-normal pt-3">{t('heading_one_title')}</h2>
            <p>{t('heading_one_text')}</p>
          </div>
          <div class="col-lg-4 text-center heading-col">
            <img src={Heading2} class="rounded-circle-own" alt="Heading Two" />
            <h2 class="fw-normal pt-3">{t('heading_two_title')}</h2>
            <p>{t('heading_two_text')}</p>
          </div>
          <div class="col-lg-4 text-center heading-col">
            <img src={Heading3} class="rounded-circle-own" alt="Heading Three" />
            <h2 class="fw-normal pt-3">{t('heading_three_title')}</h2>
            <p>{t('heading_three_text')}</p>
          </div>
        </div>
      </div>

      <div class="container">
        <div class="row mt-5">
          <div class="col-sm-6 mb-4">
            <div class="video-container">
              <YouTube
                videoId="ao1_cU2w8IA"
                class="video-frame"
                opts={{
                  height: '360',
                  width: '640',
                  playerVars: {
                    autoplay: 0,
                  },
                }}
                onReady={onPlayerReady}
              />
              {!isPlaying && (
                <div class="play-button" onClick={handlePlayButtonClick}>
                  <div class="play-icon" />
                </div>
              )}
            </div>
          </div>
          <div class="col-sm-6">
            <h2>{t('about_us_title')}</h2>
            <hr />
            <p class="pl-5 text-indent">{t('about_us_text')}</p>
          </div>
        </div>
      </div>
      <div class="container text-center">
        <h1>{t('our_team_administration')}</h1>
        <hr />
        <div class="row justify-content-center">
          {administration.map((admin) => (
            <div class="card our-card" key={admin.id}>
              <img src={admin.image} class="card-img-top our-card-img-top" alt={admin.position} />
              <div class="card-body">
                <h5 class="card-title">{admin.position}</h5>
                <p class="card-text">{admin.fullname}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Home;
