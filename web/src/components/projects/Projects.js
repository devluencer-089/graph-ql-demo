import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import { withStyles } from 'material-ui/styles';

export const GET_ALL_PROJECTS = gql`
  {
    query
    goes
    here
  }
`;

const styles = theme => ({
  root: {
    width: '100%',
    backgroundColor: theme.palette.background.paper
  }
});

export class Projects extends Component {
  /*render() {
    return (
      <Query query={GET_ALL_PROJECTS}>
        {({ loading, error, data }) => {
          if (loading) return 'Loading...';
          if (error) return `Error! ${error.message}`;

          // insert data
        }}
      </Query>
    );
  }*/

  render() {
    return <div />;
  }
}

export default withStyles(styles)(Projects);
