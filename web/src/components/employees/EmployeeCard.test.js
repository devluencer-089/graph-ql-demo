import React from 'react';
import { createShallow } from 'material-ui/test-utils';
import EmployeeCard from './EmployeeCard';

describe('EmployeeCard Component', () => {
  let shallow = createShallow({ dive: true });
  let employee = {
    id: '3',
    level: 'SENIOR_DEVELOPER',
    firstname: 'Si',
    lastname: 'Tran'
  };

  it('should match snapshot', () => {
    let eut = shallow(<EmployeeCard employee={employee} />);

    expect(eut).toMatchSnapshot();
  });
});
