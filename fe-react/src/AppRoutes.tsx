import React from 'react';
import { Routes, Route } from 'react-router-dom';
import SystemConfigurations from './pages/SystemConfigurations';
import TicketActions from './pages/TicketActions';
import InternalRoutes from './constants/InternalRoutes';

const AppRoutes = () => (
  <Routes>
    <Route path={InternalRoutes.DEFAULT} element={<SystemConfigurations />} />
    <Route path={InternalRoutes.TICKET_ACTIONS} element={<TicketActions />} />
  </Routes>
);

export default AppRoutes;
