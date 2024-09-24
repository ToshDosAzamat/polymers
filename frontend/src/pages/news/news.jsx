import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import Heading from '../../component/heading/heading';
import Navbar from '../../component/navbar/navbar';
import Footer from '../../component/footer/footer';
import { getNews } from '../../service/apiNews';
import Carousel from '../../component/carousel/carousel';
import './news.css';

import { PulseLoader } from 'react-spinners';

const extractBase64Image = (imageObject) => {
    return imageObject ? `data:image/jpeg;base64,${imageObject.img}` : null;
};

const News = () => {
    const { t, i18n } = useTranslation();
    const [newsList, setNewsList] = useState([]);
    const [selectedNews, setSelectedNews] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [loading, setLoading] = useState(true);
    const newsPerPage = 10;

    useEffect(() => {
        const fetchNews = async () => {
            try {
                setLoading(true);
                const newslist = await getNews(i18n.language);
                const newsWithBase64Images = newslist.map(news => ({
                    ...news,
                    images: Array.isArray(news.images)
                        ? news.images.map(image => extractBase64Image(image))
                        : []
                }));
                setNewsList(newsWithBase64Images);
                setSelectedNews(newsWithBase64Images.slice(-1)[0]);
            } catch (error) {
                console.error('Error fetching news:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchNews();
    }, [i18n.language]);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    const handleNewsClick = (news) => {
        setSelectedNews(news);
    };

    const indexOfLastNews = currentPage * newsPerPage;
    const indexOfFirstNews = indexOfLastNews - newsPerPage;
    const currentNews = newsList.slice(indexOfFirstNews, indexOfLastNews);

    const totalPages = Math.ceil(newsList.length / newsPerPage);

    return (
        <div>
            <Heading />
            <Navbar />
            <div className="container mt-5">
                <div className={`row ${loading ? 'loading-overlay' : ''}`}>
                    {loading && (
                        <div className="loading-spinner">
                            <PulseLoader color="#000" />
                        </div>
                    )}
                    <div className="col-sm-4">
                        {!loading && (
                            <>
                                <h2>{t('news_left_about')}</h2>
                                <hr />
                                <div className="overflow-x-scroll">
                                    <div className="list-group">
                                        {currentNews.map((news) => (
                                            <div key={news.id}>
                                                <button
                                                    className="list-group-item list-group-item-action"
                                                    onClick={() => handleNewsClick(news)}
                                                >
                                                    <b>{news.title}</b>
                                                    <p className="float-end">
                                                        <b className='me-1'>{t('statement_right_date')}:</b>{news.date}
                                                    </p>
                                                </button>
                                            </div>
                                        ))}
                                    </div>
                                    <nav aria-label="Page navigation example justify-content-center">
                                        <ul className="pagination mt-3">
                                            <li className="page-item">
                                                <button
                                                    className="page-link"
                                                    onClick={() => handlePageChange(currentPage - 1)}
                                                    disabled={currentPage === 1}
                                                    aria-label="Previous"
                                                >
                                                    <span aria-hidden="true">&laquo;</span>
                                                </button>
                                            </li>
                                            {Array.from({ length: totalPages }, (_, index) => (
                                                <li className={`page-item ${currentPage === index + 1 ? 'active' : ''}`} key={index}>
                                                    <button
                                                        className="page-link"
                                                        onClick={() => handlePageChange(index + 1)}
                                                    >
                                                        {index + 1}
                                                    </button>
                                                </li>
                                            ))}
                                            <li className="page-item">
                                                <button
                                                    className="page-link"
                                                    onClick={() => handlePageChange(currentPage + 1)}
                                                    disabled={currentPage === totalPages}
                                                    aria-label="Next"
                                                >
                                                    <span aria-hidden="true">&raquo;</span>
                                                </button>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </>
                        )}
                    </div>
                    <div className="col-sm-8 justify-content-center">
                        {!loading && (
                            <>
                                <h2>{t('news_right_about')}</h2>
                                <hr />
                                <div className="container">
                                    {selectedNews && selectedNews.images.length > 0 && (
                                        <div>
                                            <Carousel images={selectedNews.images} />
                                            <p className='fs-4 mt-5'>
                                                <b>{selectedNews ? selectedNews.title : ''}</b>
                                                <p className='float-end'>
                                                    <b className='me-2'>{t('statement_right_date')}:</b>{selectedNews.date}
                                                </p>
                                            </p>
                                            <hr />
                                            <p className='order-text'>{selectedNews.text}</p>
                                        </div>
                                    )}
                                </div>
                            </>
                        )}
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
};

export default News;
