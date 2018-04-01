import React from 'react';
import { createShallow } from 'material-ui/test-utils';
import Users from './Users';

describe('Users Component', () => {
  let shallow = createShallow();

  it('should match snapshot', () => {
    let eut = shallow(<Users />);

    expect(eut).toMatchSnapshot();
  });
});
