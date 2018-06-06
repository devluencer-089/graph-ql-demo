import React from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import { withStyles } from 'material-ui/styles';

export const query = gql`
  {
    query
    goes
    here
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
  //return <Query query={}/>

  return <div />;
}

export default withStyles(styles)(EmployeeDetails);
