const express = require('express');
const path = require('path');
const app = express();

// Serve static files
app.use(express.static(path.join(__dirname, 'dist')));

// Handle client-side routing, return all requests to Vue app
app.get('*', function(req, res) {
    res.sendFile(path.resolve(__dirname, 'dist', 'index.html'));
});

const port = 8086;
app.listen(port);

console.log(`Server started on port ${port}`);