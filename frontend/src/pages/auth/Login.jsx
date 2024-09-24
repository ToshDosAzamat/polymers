import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './login.css';
import mainlogo from '../../component/images/mainlogo.png';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('https://toshdosazamat-polimerlar-backend-ab3e.twc1.net/api/auth/login', { email, password });
            // const response = await axios.post('http://localhost:8080/api/auth/login', { email, password });

            localStorage.setItem('token', response.data.token);
            localStorage.setItem('tokenType', response.data.type);
            navigate('/adminpanel');
        } catch (err) {
            setError('Invalid email or password');
        }
    };
    

    return (
        <div className='own-container d-flex bg-secondary'>
            <form onSubmit={handleLogin} className='own-form justify-content-center text-center d-flex align-items-center'>
                <div className='p-5 bg-primary'>
                    <img className='mainlogo m-2' src={mainlogo} alt="" />
                    <input
                        className='own-input w-100 p-2'
                        type="email"
                        value={email}
                        placeholder='email'
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <input
                        className='own-input w-100 p-2'
                        type="password"
                        value={password}
                        placeholder='password'
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    {error && <p style={{ color: 'red' }}>{error}</p>}
                    <button className='login-button btn btn-success w-100 mt-3' type="submit">Login</button>
                </div>

            </form>
        </div>
    );
}

export default Login;
