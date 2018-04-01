import React from 'react';
import List, { ListItem, ListItemIcon, ListItemText } from 'material-ui/List';
import Divider from 'material-ui/Divider';
import Typography from 'material-ui/Typography';
import UsersIcon from 'material-ui-icons/People';
import { withStyles } from 'material-ui/styles';
import { Link } from 'react-router-dom';

const styles = () => ({
  root: {
    width: '100%',
    maxWidth: 480
  }
});

const listOfMenuEntries = [
  {
    label: 'Alle User',
    icon: <UsersIcon />,
    value: '/users'
  }
];

export const Sidebar = props => {
  const { classes } = props;

  return (
    <div className={classes.root}>
      <List component="nav">
        {listOfMenuEntries.map(entry => (
          <div key={entry.label}>
            <Link to={entry.value} style={{ textDecoration: 'none' }}>
              <ListItem button>
                <ListItemIcon>{entry.icon}</ListItemIcon>
                <ListItemText
                  disableTypography
                  primary={<Typography type="h3">{entry.label}</Typography>}
                />
              </ListItem>
            </Link>
          </div>
        ))}
      </List>
      <Divider />
    </div>
  );
};

export default withStyles(styles)(Sidebar);
