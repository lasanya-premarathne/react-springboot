import { Box, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Typography } from '@mui/material';
import { FC } from 'react';

// Example data for the logs
const logData = [
    { type: 'Info', time: '10:00 AM', message: 'System started successfully.' },
    { type: 'Warning', time: '10:05 AM', message: 'High memory usage detected.' },
    { type: 'Error', time: '10:10 AM', message: 'Unable to connect to the database.' },
];

const Logs: FC = () => {
    return (
        <Box sx={{ padding: 2 }}>
            <Typography variant="h6" gutterBottom>
                Logs
            </Typography>
            <TableContainer component={Paper}>
                <Table size='small' stickyHeader>
                    <TableHead>
                        <TableRow>
                            <TableCell>Type</TableCell>
                            <TableCell>Time</TableCell>
                            <TableCell>Message</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {logData.map((log, index) => (
                            <TableRow key={index}>
                                <TableCell>{log.type}</TableCell>
                                <TableCell>{log.time}</TableCell>
                                <TableCell>{log.message}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default Logs;
