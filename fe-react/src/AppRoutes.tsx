import React from 'react';
import { Routes, Route } from 'react-router-dom';
import SystemConfigurations from './pages/SystemConfigurations';
import TicketActions from './pages/TicketActions';

const AppRoutes = () => (
  <Routes>
    <Route path="/" element={<SystemConfigurations />} />
    <Route path="/ticket-actions" element={<TicketActions />} />
  </Routes>
);

export default AppRoutes;
