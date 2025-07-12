import React, { FC, useState } from 'react';
import { Typography, TextField, Button, Grid, Container } from '@mui/material';
import { Configurations } from '../models/configurations.model';
import ConfigurationValidation from '../validations/ConfingurationValidation';
import { useNavigate } from 'react-router-dom';

const SystemConfigurations: FC = () => {
	const navigate = useNavigate();

	const [formData, setFormData] = useState<Configurations>({
		eventId: 1,
		eventName: '',
		totalTickets: undefined,
		ticketReleaseRate: undefined,
		customerRetrievalRate: undefined,
		maxTicketCapacity: undefined,
	});

	const [errors, setErrors] = useState({
		eventName: '',
		totalTickets: '',
		ticketReleaseRate: '',
		customerRetrievalRate: '',
		maxTicketCapacity: '',
	});

	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setFormData({
			...formData,
			[e.target.name]: e.target.value,
		});
	};

	const handleSubmit = (e: React.FormEvent) => {
		// Prevents the page from reloading on form submit
		e.preventDefault();

		const isValid = ConfigurationValidation({
			formData,
			errors,
			setErrors,
		});

		if (isValid) {
			// Redirect to ticket-actions route
			navigate('/ticket-actions');
			console.log('Form submitted:', formData);
		}
	};

	return (
		<Container
			maxWidth='sm'
			sx={{
				display: 'flex',
				flexDirection: 'column',
				justifyContent: 'center',
				alignItems: 'center',
				height: '100vh',
			}}
		>
			<Typography variant='h3' gutterBottom textAlign='center'>
				Ticketing System
			</Typography>
			<Typography variant='h6' gutterBottom textAlign='center'>
				System Configurations
			</Typography>
			<form onSubmit={(e) => e.preventDefault()}>
				<Grid container spacing={3}>
					<Grid item xs={12}>
						<TextField
							label='Event Name'
							variant='outlined'
							fullWidth
							name='eventName'
							value={formData.eventName}
							onChange={handleChange}
							error={!!errors.eventName}
							helperText={errors.eventName}
						/>
					</Grid>

					<Grid item xs={12}>
						<TextField
							label='Total Number of Tickets'
							variant='outlined'
							fullWidth
							name='totalTickets'
							type='number'
							value={formData.totalTickets}
							onChange={handleChange}
							error={!!errors.totalTickets}
							helperText={errors.totalTickets}
						/>
					</Grid>

					<Grid item xs={12}>
						<TextField
							label='Ticket Release Rate'
							variant='outlined'
							fullWidth
							name='ticketReleaseRate'
							type='number'
							value={formData.ticketReleaseRate}
							onChange={handleChange}
							error={!!errors.ticketReleaseRate}
							helperText={errors.ticketReleaseRate}
						/>
					</Grid>

					<Grid item xs={12}>
						<TextField
							label='Customer Retrieval Rate'
							variant='outlined'
							fullWidth
							name='customerRetrievalRate'
							type='number'
							value={formData.customerRetrievalRate}
							onChange={handleChange}
							error={!!errors.customerRetrievalRate}
							helperText={errors.customerRetrievalRate}
						/>
					</Grid>

					<Grid item xs={12}>
						<TextField
							label='Maximum Ticket Capacity'
							variant='outlined'
							fullWidth
							name='maxTicketCapacity'
							type='number'
							value={formData.maxTicketCapacity}
							onChange={handleChange}
							error={!!errors.maxTicketCapacity}
							helperText={errors.maxTicketCapacity}
						/>
					</Grid>

					<Grid item xs={12}>
						<Button
							variant='contained'
							color='primary'
							fullWidth
							onClick={handleSubmit}
						>
							Set Configurations
						</Button>
					</Grid>
				</Grid>
			</form>
		</Container>
	);
};

export default SystemConfigurations;
