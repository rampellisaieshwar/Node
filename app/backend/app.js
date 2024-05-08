// const express = require('express');
// const mongoose = require('mongoose');
// const bodyParser = require('body-parser');

// const app = express();

// // Connect to MongoDB
// // mongoose.connect('mongodb://localhost/myapp', {
// // mongoose.connect('mongodb://localhost:27018/myapp', {
// //     useNewUrlParser: true,
// //     useUnifiedTopology: true
// // })
// // .then(() => console.log('Connected to MongoDB'))
// // .catch(err => console.error('Error connecting to MongoDB:', err));
// mongoose.connect('mongodb://localhost:27017/myapp', {
//     useNewUrlParser: true,
//     useUnifiedTopology: true
// })
// .then(() => console.log('Connected to MongoDB'))
// .catch(err => console.error('Error connecting to MongoDB:', err));

// // Create a User model
// const User = mongoose.model('User', {
//     username: String,
//     email: String,
//     password: String
// });

// // Middleware
// app.use(bodyParser.json());

// // Routes
// app.get('/', (req, res) => {
//     res.send('Welcome to the backend server!');
// });

// app.post('/api/register', async (req, res) => {
//     try {
//         const { username, email, password } = req.body;
//         const user = new User({ username, email, password });
//         await user.save();
//         res.json({ success: true, message: 'Registration successful' });
//     } catch (err) {
//         console.error('Error registering user:', err);
//         res.status(500).json({ success: false, message: 'Registration failed' });
//     }
// });

// app.post('/api/login', async (req, res) => {
//     try {
//         const { username, password } = req.body;
//         const user = await User.findOne({ username, password });
//         if (user) {
//             res.json({ success: true, message: 'Login successful' });
//         } else {
//             res.status(401).json({ success: false, message: 'Invalid credentials' });
//         }
//     } catch (err) {
//         console.error('Error logging in:', err);
//         res.status(500).json({ success: false, message: 'Login failed' });
//     }
// });

// // Endpoint for fetching user profile data
// app.get('/api/profile', async (req, res) => {
//     try {
//         const { username } = req.query;
//         const user = await User.findOne({ username });
//         if (user) {
//             res.json(user);
//         } else {
//             res.status(404).json({ message: 'User not found' });
//         }
//     } catch (err) {
//         console.error('Error fetching user profile:', err);
//         res.status(500).json({ message: 'Internal server error' });
//     }
// });

// // Endpoint for updating user's email
// app.put('/api/update-email', async (req, res) => {
//     try {
//         const { username, email } = req.body;

//         // Update the user document in the database
//         const updatedUser = await User.findOneAndUpdate(
//             { username },
//             { email },
//             { new: true }
//         );

//         if (updatedUser) {
//             res.json({ success: true, message: 'Email updated successfully' });
//         } else {
//             res.status(404).json({ success: false, message: 'User not found' });
//         }
//     } catch (err) {
//         console.error('Error updating email:', err);
//         res.status(500).json({ success: false, message: 'Internal server error' });
//     }
// });

// // Endpoint for updating user's password
// app.put('/api/update-password', async (req, res) => {
//     try {
//         const { username, password } = req.body;

//         // Update the user document in the database
//         const updatedUser = await User.findOneAndUpdate(
//             { username },
//             { password },
//             { new: true }
//         );

//         if (updatedUser) {
//             res.json({ success: true, message: 'Password updated successfully' });
//         } else {
//             res.status(404).json({ success: false, message: 'User not found' });
//         }
//     } catch (err) {
//         console.error('Error updating password:', err);
//         res.status(500).json({ success: false, message: 'Internal server error' });
//     }
// });

// // Start the server
// app.listen(3000, () => {
//     console.log('Server started on port 3000');
// });


// const express = require('express');
// const mongoose = require('mongoose');
// const bodyParser = require('body-parser');

// const app = express();

// // Connect to MongoDB
// mongoose.connect('mongodb://localhost:27017/myapp', {
//     useNewUrlParser: true,
//     useUnifiedTopology: true
// })
// .then(() => console.log('Connected to MongoDB'))
// .catch(err => console.error('Error connecting to MongoDB:', err));

// // Create a User model
// const User = mongoose.model('User', {
//     username: String,
//     email: String,
//     password: String,
//     fullName: String,
//     phone: String
// });

// // Middleware
// app.use(bodyParser.json());

// // Routes
// app.get('/', (req, res) => {
//     res.send('Welcome to the backend server!');
// });

// app.post('/api/register', async (req, res) => {
//     try {
//         const { username, email, password, fullName, phone } = req.body;
//         const user = new User({ username, email, password, fullName, phone });
//         await user.save();
//         res.json({ success: true, message: 'Registration successful' });
//     } catch (err) {
//         console.error('Error registering user:', err);
//         res.status(500).json({ success: false, message: 'Registration failed' });
//     }
// });

// app.post('/api/login', async (req, res) => {
//     try {
//         const { username, password } = req.body;
//         const user = await User.findOne({ username, password });
//         if (user) {
//             res.json({ success: true, message: 'Login successful' });
//         } else {
//             res.status(401).json({ success: false, message: 'Invalid credentials' });
//         }
//     } catch (err) {
//         console.error('Error logging in:', err);
//         res.status(500).json({ success: false, message: 'Login failed' });
//     }
// });

// // Endpoint for fetching user profile data
// app.get('/api/profile', async (req, res) => {
//     try {
//         const { username } = req.query;
//         const user = await User.findOne({ username });
//         if (user) {
//             res.json({
//                 username: user.username,
//                 email: user.email,
//                 fullName: user.fullName,
//                 phone: user.phone
//             });
//         } else {
//             res.status(404).json({ message: 'User not found' });
//         }
//     } catch (err) {
//         console.error('Error fetching user profile:', err);
//         res.status(500).json({ message: 'Internal server error' });
//     }
// });

// // Endpoint for updating user's email
// app.put('/api/update-email', async (req, res) => {
//     try {
//         const { username, email } = req.body;

//         // Update the user document in the database
//         const updatedUser = await User.findOneAndUpdate(
//             { username },
//             { email },
//             { new: true }
//         );

//         if (updatedUser) {
//             res.json({ success: true, message: 'Email updated successfully' });
//         } else {
//             res.status(404).json({ success: false, message: 'User not found' });
//         }
//     } catch (err) {
//         console.error('Error updating email:', err);
//         res.status(500).json({ success: false, message: 'Internal server error' });
//     }
// });

// // Endpoint for updating user's password
// app.put('/api/update-password', async (req, res) => {
//     try {
//         const { username, password } = req.body;

//         // Update the user document in the database
//         const updatedUser = await User.findOneAndUpdate(
//             { username },
//             { password },
//             { new: true }
//         );

//         if (updatedUser) {
//             res.json({ success: true, message: 'Password updated successfully' });
//         } else {
//             res.status(404).json({ success: false, message: 'User not found' });
//         }
//     } catch (err) {
//         console.error('Error updating password:', err);
//         res.status(500).json({ success: false, message: 'Internal server error' });
//     }
// });

// // Start the server
// app.listen(3000, () => {
//     console.log('Server started on port 3000');
// });


const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');

const app = express();

// Connect to MongoDB
mongoose.connect('mongodb://localhost:27017/myapp', {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
.then(() => console.log('Connected to MongoDB'))
.catch(err => console.error('Error connecting to MongoDB:', err));

// Create a User model
const User = mongoose.model('User', {
    username: String,
    email: String,
    password: String,
    fullName: String,
    phone: String
});
// Middleware
app.use(bodyParser.json());

// Routes
app.get('/', (req, res) => {
    res.send('Welcome to the backend server!');
});

app.post('/api/register', async (req, res) => {
    try {
        const { username, email, password, fullName, phone } = req.body;
        const user = new User({ username, email, password, fullName, phone });
        await user.save();
        res.json({ success: true, message: 'Registration successful' });
    } catch (err) {
        console.error('Error registering user:', err);
        res.status(500).json({ success: false, message: 'Registration failed' });
    }
});

app.post('/api/login', async (req, res) => {
    try {
        const { username, password } = req.body;
        const user = await User.findOne({ username, password });
        if (user) {
            res.json({ success: true, message: 'Login successful' });
        } else {
            res.status(401).json({ success: false, message: 'Invalid credentials' });
        }
    } catch (err) {
        console.error('Error logging in:', err);
        res.status(500).json({ success: false, message: 'Login failed' });
    }
});

// Endpoint for fetching user profile data
app.get('/api/profile', async (req, res) => {
    try {
        const { username } = req.query;
        const user = await User.findOne({ username });
        if (user) {
            res.json({
                username: user.username,
                email: user.email,
                fullName: user.fullName,
                phone: user.phone
            });
        } else {
            res.status(404).json({ message: 'User not found' });
        }
    } catch (err) {
        console.error('Error fetching user profile:', err);
        res.status(500).json({ message: 'Internal server error' });
    }
});

// Endpoint for updating user's email
app.put('/api/update-email', async (req, res) => {
    try {
        const { username, email } = req.body;

        // Update the user document in the database
        const updatedUser = await User.findOneAndUpdate(
            { username },
            { email },
            { new: true }
        );

        if (updatedUser) {
            res.json({ success: true, message: 'Email updated successfully' });
        } else {
            res.status(404).json({ success: false, message: 'User not found' });
        }
    } catch (err) {
        console.error('Error updating email:', err);
        res.status(500).json({ success: false, message: 'Internal server error' });
    }
});

// Endpoint for updating user's fullname
app.put('/api/update-fullname', async (req, res) => {
    try {
        const { username, fullName } = req.body;

        // Update the user document in the database
        const updatedUser = await User.findOneAndUpdate(
            { username },
            { fullName },
            { new: true }
        );

        if (updatedUser) {
            res.json({ success: true, message: 'Fullname updated successfully' });
        } else {
            res.status(404).json({ success: false, message: 'User not found' });
        }
    } catch (err) {
        console.error('Error updating fullname:', err);
        res.status(500).json({ success: false, message: 'Internal server error' });
    }
});

// Endpoint for updating user's phone
app.put('/api/update-phone', async (req, res) => {
    try {
        const { username, phone } = req.body;

        // Update the user document in the database
        const updatedUser = await User.findOneAndUpdate(
            { username },
            { phone },
            { new: true }
        );

        if (updatedUser) {
            res.json({ success: true, message: 'Phone updated successfully' });
        } else {
            res.status(404).json({ success: false, message: 'User not found' });
        }
    } catch (err) {
        console.error('Error updating phone:', err);
        res.status(500).json({ success: false, message: 'Internal server error' });
    }
});

// Start the server
app.listen(3000, () => {
    console.log('Server started on port 3000');
});
