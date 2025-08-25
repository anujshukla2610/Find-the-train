// Configuration
const API_BASE = 'http://localhost:8080';

// Station mapping for display names
const stationMap = {
    'NDLS': 'New Delhi',
    'BCT': 'Mumbai Central',
    'MAS': 'Chennai Central',
    'HWH': 'Howrah Junction',
    'SBC': 'Bengaluru City Junction',
    'HYB': 'Hyderabad Deccan',
    'PUNE': 'Pune Junction',
    'JP': 'Jaipur Junction',
    'LKO': 'Lucknow NR',
    'PNBE': 'Patna Junction',
    'ADI': 'Ahmedabad Junction',
    'BPL': 'Bhopal Junction'
};

// DOM Elements (will be initialized on DOMContentLoaded)
let sourceStation, destinationStation, resultsContainer, statusDiv;
let trainsList, resultsTitle, resultsCount;

/**
 * Initialize database with sample data
 */
async function initializeDatabase() {
    statusDiv.innerHTML = '<div class="loading">Initializing database...</div>';
    
    try {
        const response = await fetch(`${API_BASE}/test`, {
            method: 'GET',
            headers: {
                'Accept': 'text/plain'
            }
        });
        
        if (response.ok) {
            const message = await response.text();
            statusDiv.innerHTML = `<div class="success">✅ ${message}</div>`;
            
            // Hide success message after 3 seconds
            setTimeout(() => {
                statusDiv.innerHTML = '';
            }, 3000);
        } else {
            const errorText = await response.text();
            throw new Error(`HTTP ${response.status}: ${errorText}`);
        }
    } catch (error) {
        console.error('Database initialization error:', error);
        statusDiv.innerHTML = `<div class="error">❌ Error initializing database: ${error.message}<br>Make sure your Spring Boot backend is running on ${API_BASE}</div>`;
    }
}

/**
 * Search for trains between source and destination
 */
async function searchTrains() {
    const sourceCode = sourceStation.value;
    const destinationCode = destinationStation.value;
    
    // Validation
    if (!sourceCode || !destinationCode) {
        showError('Please select both source and destination stations');
        return;
    }
    
    if (sourceCode === destinationCode) {
        showError('Source and destination stations cannot be the same');
        return;
    }
    
    // Clear previous results and show loading
    statusDiv.innerHTML = '<div class="loading">Searching for trains...</div>';
    resultsContainer.style.display = 'none';
    
    try {
        const searchUrl = `${API_BASE}/search/by-code?sourceCode=${encodeURIComponent(sourceCode)}&destinationCode=${encodeURIComponent(destinationCode)}`;
        console.log('Searching:', searchUrl);
        
        const response = await fetch(searchUrl, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        
        if (response.ok) {
            const trains = await response.json();
            statusDiv.innerHTML = '';
            
            console.log('Search results:', trains);
            
            if (trains && trains.length > 0) {
                displayTrains(trains, sourceCode, destinationCode);
                resultsContainer.style.display = 'block';
                
                // Update results header
                const sourceStationName = getStationName(sourceCode);
                const destStationName = getStationName(destinationCode);
                resultsTitle.textContent = `${sourceStationName} → ${destStationName}`;
                resultsCount.textContent = `${trains.length} train${trains.length > 1 ? 's' : ''} found`;
                
                // Scroll to results
                resultsContainer.scrollIntoView({ behavior: 'smooth' });
            } else {
                displayNoResults(sourceCode, destinationCode);
                resultsContainer.style.display = 'block';
                resultsContainer.scrollIntoView({ behavior: 'smooth' });
            }
        } else {
            const errorText = await response.text();
            throw new Error(`HTTP ${response.status}: ${errorText}`);
        }
    } catch (error) {
        console.error('Search error:', error);
        let errorMessage = `Error searching trains: ${error.message}`;
        
        if (error.message.includes('Failed to fetch')) {
            errorMessage += '<br><br>Possible causes:<br>• Backend server is not running<br>• CORS is not properly configured<br>• Wrong API URL';
        }
        
        statusDiv.innerHTML = `<div class="error">❌ ${errorMessage}</div>`;
    }
}

/**
 * Display search results
 */
function displayTrains(trains, sourceCode, destinationCode) {
    const sourceStationName = getStationName(sourceCode);
    const destStationName = getStationName(destinationCode);
    
    trainsList.innerHTML = trains.map((trainSchedule, index) => {
        // Handle different response structures
        const train = trainSchedule.train || trainSchedule;
        const departureTime = trainSchedule.departureTime || 'N/A';
        const arrivalTime = trainSchedule.arrivalTime || 'N/A';
        const trainName = train.trainName || train.name || 'Unknown Train';
        const trainNumber = train.trainNumber || train.number || 'N/A';
        
        return `
            <div class="train-item" style="animation-delay: ${index * 0.1}s">
                <div class="train-header">
                    <div class="train-name">${trainName}</div>
                    <div class="train-number">${trainNumber}</div>
                </div>
                <div class="train-route">
                    <div class="station-info">
                        <div class="station-name">${sourceStationName}</div>
                        <div class="station-code">${sourceCode}</div>
                        <div class="time">🚂 ${departureTime}</div>
                    </div>
                    <div class="route-arrow">→</div>
                    <div class="station-info">
                        <div class="station-name">${destStationName}</div>
                        <div class="station-code">${destinationCode}</div>
                        <div class="time">🏁 ${arrivalTime}</div>
                    </div>
                </div>
            </div>
        `;
    }).join('');
}

/**
 * Display no results message
 */
function displayNoResults(sourceCode, destinationCode) {
    const sourceStationName = getStationName(sourceCode);
    const destStationName = getStationName(destinationCode);
    
    resultsTitle.textContent = `${sourceStationName} → ${destStationName}`;
    resultsCount.textContent = '0 trains found';
    
    trainsList.innerHTML = `
        <div class="no-results">
            <h3>🚫 No trains found</h3>
            <p>No direct trains available between <strong>${sourceStationName}</strong> and <strong>${destStationName}</strong></p>
            <p>Try searching for alternative routes or check if the database is properly initialized.</p>
            <p><em>Tip: Click "Init DB" button to populate the database with sample data</em></p>
        </div>
    `;
}

/**
 * Get station display name from code
 */
function getStationName(code) {
    return stationMap[code] || code;
}

/**
 * Show error message
 */
function showError(message) {
    statusDiv.innerHTML = `<div class="error">❌ ${message}</div>`;
    setTimeout(() => {
        statusDiv.innerHTML = '';
    }, 5000);
}

/**
 * Show success message
 */
function showSuccess(message) {
    statusDiv.innerHTML = `<div class="success">✅ ${message}</div>`;
    setTimeout(() => {
        statusDiv.innerHTML = '';
    }, 3000);
}

/**
 * Validate form inputs
 */
function validateForm() {
    const sourceCode = sourceStation.value;
    const destinationCode = destinationStation.value;
    
    // Clear previous validation styles
    sourceStation.style.borderColor = '';
    destinationStation.style.borderColor = '';
    
    let isValid = true;
    
    if (!sourceCode) {
        sourceStation.style.borderColor = '#dc3545';
        isValid = false;
    }
    
    if (!destinationCode) {
        destinationStation.style.borderColor = '#dc3545';
        isValid = false;
    }
    
    if (sourceCode === destinationCode && sourceCode) {
        sourceStation.style.borderColor = '#dc3545';
        destinationStation.style.borderColor = '#dc3545';
        isValid = false;
    }
    
    return isValid;
}

/**
 * Handle station selection change
 */
function onStationChange() {
    // Clear validation styles
    sourceStation.style.borderColor = '';
    destinationStation.style.borderColor = '';
    
    // Auto-search if both stations are selected and valid
    if (sourceStation.value && destinationStation.value && sourceStation.value !== destinationStation.value) {
        // Optional: Auto-search after a delay
        // setTimeout(() => searchTrains(), 500);
    }
}

/**
 * Handle keyboard shortcuts
 */
function handleKeyDown(event) {
    // Search on Enter key
    if (event.key === 'Enter') {
        event.preventDefault();
        if (validateForm()) {
            searchTrains();
        }
    }
    
    // Initialize database on Ctrl+I
    if (event.ctrlKey && event.key === 'i') {
        event.preventDefault();
        initializeDatabase();
    }
}

/**
 * Swap source and destination stations
 */
function swapStations() {
    const tempValue = sourceStation.value;
    sourceStation.value = destinationStation.value;
    destinationStation.value = tempValue;
    
    onStationChange();
}

/**
 * Initialize the application
 */
function initializeApp() {
    // Get DOM elements
    sourceStation = document.getElementById('sourceStation');
    destinationStation = document.getElementById('destinationStation');
    resultsContainer = document.getElementById('resultsContainer');
    statusDiv = document.getElementById('statusMessage');
    trainsList = document.getElementById('trainsList');
    resultsTitle = document.getElementById('resultsTitle');
    resultsCount = document.getElementById('resultsCount');
    
    // Add event listeners
    sourceStation.addEventListener('change', onStationChange);
    destinationStation.addEventListener('change', onStationChange);
    
    // Add keyboard support
    document.addEventListener('keydown', handleKeyDown);
    
    // Add focus/blur handlers for better UX
    sourceStation.addEventListener('focus', () => sourceStation.style.borderColor = '#667eea');
    destinationStation.addEventListener('focus', () => destinationStation.style.borderColor = '#667eea');
    
    sourceStation.addEventListener('blur', () => {
        if (sourceStation.style.borderColor === 'rgb(102, 126, 234)') {
            sourceStation.style.borderColor = '';
        }
    });
    
    destinationStation.addEventListener('blur', () => {
        if (destinationStation.style.borderColor === 'rgb(102, 126, 234)') {
            destinationStation.style.borderColor = '';
        }
    });
    
    console.log('🚂 Train Booking System initialized successfully!');
    console.log('Backend API:', API_BASE);
    console.log('Keyboard shortcuts:');
    console.log('- Enter: Search trains');
    console.log('- Ctrl+I: Initialize database');
}

// Initialize when DOM is loaded
document.addEventListener('DOMContentLoaded', initializeApp);

// Global error handler
window.addEventListener('unhandledrejection', function(event) {
    console.error('Unhandled promise rejection:', event.reason);
    showError('An unexpected error occurred. Please try again.');
});

// Make functions globally available for onclick handlers
window.searchTrains = searchTrains;
window.initializeDatabase = initializeDatabase;
window.swapStations = swapStations;