import { Box, Tabs, Tab } from "@mui/material";
import { FC, useState } from "react";
import AddCircleIcon from '@mui/icons-material/AddCircle';
import RemoveCircleIcon from '@mui/icons-material/RemoveCircle';
import SyncIcon from '@mui/icons-material/Sync';
import Vendor from './tabs/Vendor';
import Customer from './tabs/Customer';
import Stimulation from './tabs/Stimulation';

const Actions: FC = () => {
    const [value, setValue] = useState(0); 

    const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };
    return (
        <Box  sx={{padding: 2}}>
            <Box sx={{ borderBottom: 1, borderColor: 'divider', padding: 0 }}>
                <Tabs
                    value={value}
                    onChange={handleTabChange}
                    variant="fullWidth"
                >
                    <Tab icon={<AddCircleIcon />} iconPosition="start" label="Vendor" />
                    <Tab icon={<RemoveCircleIcon />} iconPosition="start" label="Customer" />
                    <Tab icon={<SyncIcon />} iconPosition="start" label="Stimulation" />
                </Tabs>
            </Box>

            <Box sx={{ marginTop: '5px', padding: 0 }}>
                {value === 0 && <Vendor />}
                {value === 1 && <Customer />}
                {value === 2 && <Stimulation />}
            </Box>
        </Box>
    )
}

export default Actions;