import * as React from 'react';
import Modal from '@mui/material/Modal';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { styled } from '@mui/material/styles';

const StyledModalContent = styled('div')({
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'white',
  boxShadow: 24,
  padding: '20px',
  borderRadius: '8px',
  backgroundColor: 'white'
});

const EditModal = ({ open, onClose, onSave, initialData }) => {
  const [editedData, setEditedData] = React.useState(initialData);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSave = () => {
    onSave(editedData);
    // console.log(editedData);
  };

  return (
    <Modal
      open={open}
      onClose={onClose}
      aria-labelledby="modal-title"
      aria-describedby="modal-description"
    >
      <StyledModalContent>
        <Typography variant="h6" component="h2" gutterBottom>
          Edit Data
        </Typography>
        <form>
          <TextField
            label="Title"
            variant="outlined"
            fullWidth
            margin="normal"
            name="title"
            value={editedData.title}
            onChange={handleInputChange}
          />
          <TextField
            label="Category ID"
            variant="outlined"
            fullWidth
            margin="normal"
            name="categoryId"
            type="number"
            inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }}
            value={editedData.categoryId}
            onChange={handleInputChange}
            />
            <TextField
            label="Inventory ID"
            variant="outlined"
            fullWidth
            margin="normal"
            name="inventoryId"
            type="number"
            inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }}
            value={editedData.inventoryId}
            onChange={handleInputChange}
            />
            <TextField
            label="Price"
            variant="outlined"
            fullWidth
            margin="normal"
            name="price"
            type="number"
            inputProps={{ inputMode: 'numeric', pattern: '^\\d*\\.?\\d*$' }}
            value={editedData.price}
            onChange={handleInputChange}
            />
          <TextField
            label="Image URL"
            variant="outlined"
            fullWidth
            margin="normal"
            name="imageUrl"
            value={editedData.imageUrl}
            onChange={handleInputChange}
          />
          <TextField
            label="Description"
            variant="outlined"
            multiline
            fullWidth
            margin="normal"
            name="description"
            value={editedData.description}
            onChange={handleInputChange}
          />
          <Button variant="contained" color="primary" onClick={handleSave}>
            Save
          </Button>
        </form>
      </StyledModalContent>
    </Modal>
  );
};

export default EditModal;
