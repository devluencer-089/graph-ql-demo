import React from 'react';
import { createShallow } from 'material-ui/test-utils';
import AppBar from './AppBar';

describe('AppBar Component', () => {
  let shallow = createShallow({ dive: true });
  it('should match snapshot', () => {
    let eut = shallow(<AppBar />);

    expect(eut).toMatchSnapshot();
  });
});
