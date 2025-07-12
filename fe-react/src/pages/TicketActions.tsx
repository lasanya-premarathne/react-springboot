import React, { FC } from 'react';
import { Grid } from '@mui/material';
import Logs from '../components/Logs';
import Charts from '../components/Charts';
import Actions from '../components/Actions';

const TicketActions: FC = () => {

    return (
        <Grid
            container
            spacing={0}
            sx={{ padding: 0}}
        >
            <Grid
                item
                xs={12}
                sm={6}
                sx={{ padding: 0 }}
                bgcolor={'#f0f4f8'}
            >
                <Actions />
            </Grid>

            <Grid
                item
                xs={12}
                sm={6}
                sx={{ padding: 0 }}
                bgcolor={'#f9f9f9'}
            >
                <Logs />
            </Grid>

            <Grid
                item
                xs={12}
                sx={{ padding: 0 }}
                bgcolor={'#e3f2fd'}
            >
                <Charts />
            </Grid>
        </Grid>
    );
};

export default TicketActions;
