import { BrowserRouter as Router } from 'react-router-dom';
import { CssBaseline } from '@mui/material';
import MainLayout from './MainLayout';

const App = () => {
    return (
        <Router>
            <CssBaseline />
            <MainLayout />
        </Router>
    );
};

export default App;
