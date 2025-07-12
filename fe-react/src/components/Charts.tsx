import React, { FC } from 'react';
import { Box } from '@mui/material';
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer
} from 'recharts';

// Example data for the line chart
const data = [
    { time: '10:00 AM', additions: 5, removals: 2 },
    { time: '10:05 AM', additions: 10, removals: 5 },
    { time: '10:10 AM', additions: 15, removals: 7 },
    { time: '10:15 AM', additions: 20, removals: 10 },
    { time: '10:20 AM', additions: 25, removals: 15 },
];

const Charts: FC = () => {
    return (
        <Box sx={{ padding: 2, height: 400 }}>
            <ResponsiveContainer width="100%" height="100%">
                <LineChart data={data} margin={{ top: 10, right: 30, left: 0, bottom: 0 }}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="time" label={{ value: 'Time', position: 'insideBottom', offset: -5 }} />
                    <YAxis label={{ value: 'Number of Tickets', angle: -90, position: 'insideLeft' }} />
                    <Tooltip />
                    <Legend />
                    <Line type="monotone" dataKey="additions" stroke="#8884d8" name="Additions" />
                    <Line type="monotone" dataKey="removals" stroke="#82ca9d" name="Removals" />
                </LineChart>
            </ResponsiveContainer>
        </Box>
    );
};

export default Charts;
