import React from 'react';
import ReactDOM from 'react-dom';
import { MuiThemeProvider } from 'material-ui/styles';

import './index.css';
import App from './App';
import theme from './theme';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(
    <MuiThemeProvider theme={theme}>
        <App />
    </MuiThemeProvider>,
    document.getElementById('root'));
registerServiceWorker();
