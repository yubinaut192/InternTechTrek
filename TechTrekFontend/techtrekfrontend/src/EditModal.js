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
  const [imageFile, setImageFile] = React.useState(null);
  const [fileName, setFileName] = React.useState('');

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
    // console.log(editedData);
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    console.log(file);
    setImageFile(file);
    setFileName(file.name); // Set the file name
  };

  const handleSave = () => {
    onSave(editedData, imageFile);
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
          <input
            accept="image/*"
            id="contained-button-file"
            type="file"
            style={{ display: 'none' }}
            onChange={handleImageChange}
          />
          <label htmlFor="contained-button-file">
            <Button variant="contained" component="span">
              Upload Image
            </Button>
          </label>
          {fileName && (
            <Typography variant="body1" gutterBottom>
              File Uploaded: {fileName}
            </Typography>
          )}
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
