const electron = require('electron');
const { app, BrowserWindow } = electron;

let mainWindow;

app.on('ready', () => {
    mainWindow = new BrowserWindow({
        width: 1000,
        height: 700
    });

    mainWindow.setTitle('Desktop App');
    mainWindow.loadURL('https://agile-sands-71066.herokuapp.com');

    mainWindow.on('closed', () => {
        mainWindow = null;
    });
});