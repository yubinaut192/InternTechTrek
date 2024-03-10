import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import EditIcon from '@mui/icons-material/Edit';
import * as React from 'react';
import Button from '@mui/material/Button';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import DeleteIcon from '@mui/icons-material/Delete';
import { useState } from 'react';
import EditModal from './EditModal'; 
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

const RecipeReviewCard = ({
  id,
  title,
  price,
  imageUrl,
  description,
  inventory_id,
  category_id
}) => {
  const [isEditModalOpen, setEditModalOpen] = useState(false);
  const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);

  const handleEdit = () => {
    setEditModalOpen(true);
  };

  const handleCloseEditModal = () => {
    setEditModalOpen(false);
  };

  const handleSaveEditModal = async (editedData) => {
    try {
        const customJson = {
            id: id,
            name: editedData.title,
            category_id: editedData.categoryId,
            inventory_id: editedData.inventoryId,
            price: editedData.price,
            desc: editedData.description
          };
      console.log(customJson);
      const response = await fetch(`http://localhost:8080/api/v1/products/updateProduct/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(customJson),
      });
  
      if (response.ok) {
        // Handle successful update
        console.log('Product updated successfully');
        window.location.reload();
      } else {
        // Handle errors
        console.error('Failed to update product');
      }
  
      setEditModalOpen(false);
    } catch (error) {
      console.error('Error updating product:', error);
    }
  };
  

  const handleDelete = () => {
    setDeleteModalOpen(true);
  };

  const handleCloseDeleteModal = () => {
    setDeleteModalOpen(false);
  };

  const handleConfirmDelete = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/v1/products/deleteByID/${id}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
      } else {
        console.error('Delete request failed:', response.statusText);
      }
    } catch (error) {
      console.error('Error during delete request:', error);
    } finally {
      setDeleteModalOpen(false); 
      window.location.reload();
    }
  };

  return (
    <>
      <Card sx={{ minWidth: 300, maxWidth: 345, border: 20, borderRadius: 8, borderColor: '#282c34' }}>
        <CardHeader
          title={title}
          subheader={"MRP Rs." + price}
        />
        <CardMedia
          component="img"
          height="194"
          image={imageUrl}
          alt="Card Image"
        />
        <CardContent>
          <Typography variant="body2" color="text.secondary">
            {description}
          </Typography>
        </CardContent>
        <CardActions disableSpacing>
          <IconButton aria-label="add to favorites" onClick={handleEdit}>
            <EditIcon />
          </IconButton>
          <IconButton aria-label="delete" onClick={handleDelete}>
            <DeleteIcon />
          </IconButton>
        </CardActions>
      </Card>

      <EditModal
        open={isEditModalOpen}
        onClose={handleCloseEditModal}
        onSave={handleSaveEditModal}
        initialData={{
          id,
          title,
          price,
          imageUrl,
          description,
          inventoryId:inventory_id,
          categoryId:category_id
        }}
      />

      <Dialog
        open={isDeleteModalOpen}
        onClose={handleCloseDeleteModal}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">Confirm Delete</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure you want to delete this item?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDeleteModal}>Cancel</Button>
          <Button onClick={handleConfirmDelete} autoFocus style={{ color: 'red' }}>
            Confirm Delete
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default RecipeReviewCard;
