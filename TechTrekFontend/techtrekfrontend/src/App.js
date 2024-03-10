// import './App.css';
// import * as React from 'react';
// import RecipeReviewCard from './Card';
// import { useEffect, useState } from 'react';
// import AddIcon from '@mui/icons-material/Add';


// // const cardData = [
// //   {
// //     title: "Shrimp and Chorizo Paella",
// //     subheader: "September 14, 2016",
// //     imageUrl: "/static/images/cards/paella.jpg",
// //     description: "This impressive paella is a perfect party dish and a fun meal to cook together with your guests. Add 1 cup of frozen peas along with the mussels, if you like.",
// //   },
// //   {
// //     title: "Shrimp and Chorizo Paella",
// //     subheader: "September 14, 2016",
// //     imageUrl: "/static/images/cards/paella.jpg",
// //     description: "This impressive paella is a perfect party dish and a fun meal to cook together with your guests. Add 1 cup of frozen peas along with the mussels, if you like.",
// //   },
// //   {
// //     title: "Shrimp and Chorizo Paella",
// //     subheader: "September 14, 2016",
// //     imageUrl: "/static/images/cards/paella.jpg",
// //     description: "This impressive paella is a perfect party dish and a fun meal to cook together with your guests. Add 1 cup of frozen peas along with the mussels, if you like.",
// //   },
// //   {
// //     title: "Shrimp and Chorizo Paella",
// //     subheader: "September 14, 2016",
// //     imageUrl: "/static/images/cards/paella.jpg",
// //     description: "This impressive paella is a perfect party dish and a fun meal to cook together with your guests. Add 1 cup of frozen peas along with the mussels, if you like.",
// //   },
// // ];

// function App() {

//   const [cardData, setCardData] = useState([]);

//   useEffect(() => {
//     const fetchData = async () => {
//       try {
//         const response = await fetch('http://localhost:8080/api/v1/products/allProducts', {
//           headers: {
//             'Authorization': 'Bearer b66e8446-4116-4a53-84da-25db5794607a',
//           },
//         });
//         const data = await response.json();
//         console.log(data);
//         setCardData(data);
//       } catch (error) {
//         console.error('Error fetching data:', error);
//       }
//     };
  
//     fetchData();
//   }, []);

//   return (
//     <div>
//       <nav className='App-Header'>
//         <h1>Doggo Warehouse</h1>
//       </nav>
//       <div className='App'>
//       {cardData.map((data, index) => (
//         <RecipeReviewCard
//           key={index}
//           id={data.id}
//           title={data.name}
//           subheader={data.price}
//           imageUrl={data.imageUrl}
//           description={data.desc}
//           category_id={data.category_id}
//           inventory_id={data.inventory_id}
//         />
//       ))}
//       </div>
//     </div>
//   );
// }

// export default App;


import './App.css';
import * as React from 'react';
import RecipeReviewCard from './Card';
import { useEffect, useState } from 'react';
import AddIcon from '@mui/icons-material/Add';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActionArea from '@mui/material/CardActionArea';
import Typography from '@mui/material/Typography';
import EditModal from './EditModal'; 


function App() {
  function addProduct() {

    console.log("add product")
  }
  
  function AddCard() {
    return (
      <Card sx={{ minWidth: 200, maxWidth: 375, minHeight:300, borderRadius: 8, border: '2px dashed #888', cursor: 'pointer', margin:3, backgroundColor: '#282c34', color: "white" }}>
        <CardActionArea onClick={handleEdit}>
          <CardContent>
            <Typography gutterBottom variant="h1" component="div" textAlign="center">
              <AddIcon fontSize="large" />
            </Typography>
            <Typography variant="h5" color="white" textAlign="center">
              Add Card
            </Typography>
          </CardContent>
        </CardActionArea>
      </Card>
    );
  }

  const [cardData, setCardData] = useState([]);
  const [isEditModalOpen, setEditModalOpen] = useState(false);

  const handleEdit = () => {
    setEditModalOpen(true);
  };

  const handleCloseEditModal = () => {
    setEditModalOpen(false);
  };

  const handleSaveEditModal = async (editedData) => {
    console.log("add model saved");
    try {
      const customJson = {
        id: 0,
        name: editedData.title,
        category_id: parseInt(editedData.categoryId, 10), 
        inventory_id: parseInt(editedData.inventoryId, 10), 
        price: parseFloat(editedData.price),
        desc: editedData.description
      };
    console.log(customJson);
    const response = await fetch(`http://localhost:8080/api/v1/products/addProduct`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(customJson),
    });

    if (response.ok) {
      // Handle successful update
      console.log('Product added successfully');
      window.location.reload();
    } else {
      // Handle errors
      console.error('Failed to add product');
    }

    setEditModalOpen(false);
  } catch (error) {
    console.error('Error updating product:', error);
  }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/v1/products/allProducts', {
          headers: {
            'Authorization': 'Bearer b66e8446-4116-4a53-84da-25db5794607a',
          },
        });
        const data = await response.json();
        console.log(data);
        setCardData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
  
    fetchData();
  }, []);

  return (
    <div>
      <nav className='App-Header'>
        <h1>Doggo Warehouse</h1>
      </nav>
      <div className='App'>
        {cardData.map((data, index) => (
          <RecipeReviewCard
            key={index}
            id={data.id}
            title={data.name}
            price={data.price}
            imageUrl={data.imageUrl}
            description={data.desc}
            category_id={data.category_id}
            inventory_id={data.inventory_id}
          />
        ))}
        <AddCard />
        <EditModal
        open={isEditModalOpen}
        onClose={handleCloseEditModal}
        onSave={handleSaveEditModal}
        initialData={{
          id:0,
          title:"",
          price:0,
          categoryId:0,
          inventoryId:0,
          imageUrl:"",
          description:"",
        }}
      />
      </div>
    </div>
  );
}

export default App;
