import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate from React Router
import SwaggerUI from 'swagger-ui-react';
import 'swagger-ui-react/swagger-ui.css';

function Adminpanel() {
    const [swaggerUrl, setSwaggerUrl] = useState('https://toshdosazamat-polimerlar-backend-ab3e.twc1.net/api-docs');
    // const [swaggerUrl, setSwaggerUrl] = useState('http://localhost:8080/api-docs');

    const [token, setToken] = useState('');
    const [tokenType, setTokenType] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const storedToken = localStorage.getItem('token');
        const storedTokenType = localStorage.getItem('tokenType');

        if (storedToken && storedTokenType) {
            setToken(storedToken);
            setTokenType(storedTokenType);
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('tokenType');
    
        setToken('');
        setTokenType('');
        navigate('/adminlogin');
    };

    const swaggerOptions = {
        url: swaggerUrl,
        requestInterceptor: (request) => {
            if (token && tokenType) {
                request.headers['Authorization:'] = `Bearer ${token}`;
            }
            return request;
        },
        docExpansion: 'none', // Optional: Keeps Swagger UI clean
        defaultModelsExpandDepth: -1 // Optional: Hides schemas in Swagger UI
    };

    return (
        <div className="container">
            <nav className="navbar bg-secondary mt-3">
                <div className="navbar-brand ms-5 fw=100">
                    Admin Panel
                </div>
                <div className="navbar-menu me-5">
                    <button onClick={handleLogout} className="btn btn-danger">
                        Logout
                    </button>
                </div>
            </nav>
            <SwaggerUI {...swaggerOptions} />
        </div>
    );
}

export default Adminpanel;
