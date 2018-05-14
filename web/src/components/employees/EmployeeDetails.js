import React from 'react';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import List, { ListItem, ListItemText, ListSubheader } from 'material-ui/List';

export const query = gql`
  query EmployeeDetailQuery($employeeId: ID) {
    getEmployee(id: $employeeId) {
      id
      level
      firstname
      lastname
      phoneNumber
      projects {
        name
      }
    }
  }
`;

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 3
  })
});

function EmployeeDetails(props) {
  const { data: { getEmployee: employee, loading }, classes } = props;
  if (loading) {
    return <div>Loading...</div>;
  }
  return (
    <div>
      <Paper className={classes.root}>
        <Typography variant="headline" component="h3">
          {employee.firstname} {employee.lastname}
        </Typography>
        <Typography variant="body1">{employee.level}</Typography>
        <Typography variant="body1">{employee.phoneNumber}</Typography>
        <List
          subheader={<ListSubheader component="div">PROJECTS</ListSubheader>}
        >
          {employee.projects &&
            employee.projects.map(project => (
              <ListItem button>
                <ListItemText primary={project.name} />
              </ListItem>
            ))}
        </List>
      </Paper>
    </div>
  );
}

export default graphql(query, {
  options: props => {
    return {
      variables: {
        employeeId: props.match.params.employeeId
      }
    };
  }
})(withStyles(styles)(EmployeeDetails));
