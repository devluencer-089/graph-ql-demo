import React from 'react';
import { createShallow } from 'material-ui/test-utils';
import EmployeeDetails, { query } from './EmployeeDetails';
import { MockedProvider } from 'react-apollo/test-utils';

describe('EmployeeDetails Component', () => {
  let shallow = createShallow({ dive: true });
  let data = {
    getEmployee: {
      id: '3',
      level: 'SENIOR_DEVELOPER',
      firstname: 'Si',
      lastname: 'Tran',
      projects: [{ name: 'TeamBank' }, { name: 'Paydirekt' }]
    }
  };
  let match = {
    params: {
      employeeId: 3
    }
  };

  it('should match snapshot', () => {
    let eut = shallow(
      <MockedProvider>
        <EmployeeDetails data={data} classes={{}} match={match} />
      </MockedProvider>
    );

    expect(eut).toMatchSnapshot();
  });

  describe('GraphQL Query', () => {
    it('should match snapshot', () => {
      expect(query).toMatchSnapshot();
    });
  });
});
