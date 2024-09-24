import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { useParams } from 'react-router-dom';
import Heading from '../../component/heading/heading';
import Navbar from '../../component/navbar/navbar';
import Footer from '../../component/footer/footer';
import { getCouncil, getStatements } from '../../service/apiCouncil';
import Carousel from '../../component/carousel/carousel';
import './council.css';

// Spinner component (You can replace this with any spinner of your choice)
import { PulseLoader } from 'react-spinners';

const extractBase64Image = (imageObject) => {
    return imageObject ? `data:image/jpeg;base64,${imageObject.img}` : null;
};

const Council = () => {
    const { t, i18n } = useTranslation();
    const { councilId } = useParams();
    const [council, setCouncil] = useState(null);
    const [statements, setStatements] = useState([]);
    const [selectedStatement, setSelectedStatement] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [loading, setLoading] = useState(true);
    const statementsPerPage = 5;

    useEffect(() => {
        const fetchCouncil = async () => {
            try {
                setLoading(true);
                const council = await getCouncil(councilId, i18n.language);
                const councilWithBase64Image = {
                    ...council,
                    image: council.image ? extractBase64Image(council.image) : null
                };
                setCouncil(councilWithBase64Image);
            } catch (error) {
                console.error('Error fetching Council:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchCouncil();
    }, [i18n.language, councilId]);

    useEffect(() => {
        const fetchStatements = async () => {
            try {
                setLoading(true);
                const statements = await getStatements(councilId, i18n.language);
                const statementsWithBase64Images = statements.map(statement => ({
                    ...statement,
                    images: Array.isArray(statement.images)
                        ? statement.images.map(image => extractBase64Image(image))
                        : []
                }));
                setStatements(statementsWithBase64Images);
                setSelectedStatement(statementsWithBase64Images.slice(-1)[0]); // Set the last statement by default
            } catch (error) {
                console.error('Error fetching statements:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchStatements();
    }, [i18n.language, councilId]);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    const handleStatementClick = (statement) => {
        setSelectedStatement(statement);
    };

    const indexOfLastStatement = currentPage * statementsPerPage;
    const indexOfFirstStatement = indexOfLastStatement - statementsPerPage;
    const currentStatements = statements.slice(indexOfFirstStatement, indexOfLastStatement);

    const totalPages = Math.ceil(statements.length / statementsPerPage);

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
                                {council && (
                                    <div>
                                        <h2>{t('council_left_about')}</h2>
                                        <hr />
                                        {council.image && (
                                            <img src={council.image} className="card-img-top mb-4 lab-card-img-top" alt={council.name} />
                                        )}
                                        <h4>{council.name}</h4>
                                        <p className="pl-5 text-decoration-p mb-5 text-indent">{council.description}</p>
                                        <h4>{t('council_left_statement_title')}</h4>
                                        <hr />
                                    </div>
                                )}

                                <div className="overflow-x-scroll">
                                    <div className="list-group">
                                        {currentStatements.map((statement) => (
                                            <div key={statement.id}>
                                                <button
                                                    className="list-group-item list-group-item-action"
                                                    onClick={() => handleStatementClick(statement)}
                                                >
                                                    <b>{statement.name}</b>
                                                    <p className="float-end">
                                                        <b className='me-1'>{t('statement_right_date')}:</b>{statement.date}
                                                    </p>
                                                </button>
                                            </div>
                                        ))}
                                    </div>
                                    <nav aria-label="Page navigation example">
                                        <ul className="pagination">
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
                                <h2>{t('council_right_about')}</h2>
                                <hr />
                                <div className="container">
                                    {selectedStatement && selectedStatement.images.length > 0 && (
                                        <div>
                                            <Carousel images={selectedStatement.images} />
                                            <p className='fs-4 mt-5'>
                                                <b>{selectedStatement ? selectedStatement.name : ''}</b>
                                                <p className='float-end'>
                                                    <b className='me-2'>{t('statement_right_date')}:</b>{selectedStatement.date}
                                                </p>
                                            </p>
                                            <hr />
                                            <p className='order-text'>{selectedStatement.description}</p>
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

export default Council;
