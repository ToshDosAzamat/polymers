import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './pages/home/home';
import Error from './pages/error/error';
import Lab from './pages/lab/lab';
import Council from "./pages/council/council";
import News from './pages/news/news';
import Contact from './pages/contact/contact';
import Services from './pages/services/services';
import Adminpanel from './pages/adminpanel/adminpanel';
import PrivateRoute from './pages/auth/PrivateRoute';
import Login from './pages/auth/Login';

function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path='/lab/:labId' element={<Lab/>}/>
          <Route path='/council/:councilId' element={<Council/>}/>
          <Route path='/news' element={<News/>}/>
          <Route path='/contact' element={<Contact/>}/>
          <Route path='/service' element={<Services/>}/>
          <Route path="/*" element={<Error/>} />
          <Route path="/" element={<Home />} />
          <Route path='/lab/:labId' element={<Lab />} />
          <Route path='/council/:councilId' element={<Council />} />
          <Route path='/news' element={<News />} />
          <Route path='/contact' element={<Contact />} />
          <Route path='/service' element={<Services />} />
          <Route path="/adminlogin" element={<Login />} />
          <Route path="/adminpanel" element={
            <PrivateRoute>
              <Adminpanel />
            </PrivateRoute>
          } />
          <Route path="*" element={<Error />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
