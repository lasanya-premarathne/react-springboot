import { Box } from "@mui/material";
import { useLocation } from "react-router-dom";
import AppRoutes from "./AppRoutes";
import Header from "./components/Header";

const MainLayout = () => {
    // Check if the path has anything after the port number
    const location = useLocation();
    const showHeader = location.pathname !== '/';

    return (
        <Box sx={{ height: '100vh', width: '100vw' }}>
            {showHeader && <Header />}
            <AppRoutes />
        </Box>
    );
};

export default MainLayout;