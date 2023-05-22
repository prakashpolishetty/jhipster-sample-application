import { IAddress, NewAddress } from './address.model';

export const sampleWithRequiredData: IAddress = {
  id: 88754,
  lineOne: 'Bulgarian application Avon',
};

export const sampleWithPartialData: IAddress = {
  id: 93117,
  lineOne: 'Hill Rubber',
  lineTwo: 'navigating Cape Missouri',
  state: 'Outdoors',
  country: 'Singapore',
  pinCode: '30000',
};

export const sampleWithFullData: IAddress = {
  id: 15053,
  lineOne: 'black',
  lineTwo: 'District',
  aptNo: 'Chair viral Soap',
  city: 'Rowlett',
  state: 'intranet',
  country: 'China',
  pinCode: '70000',
};

export const sampleWithNewData: NewAddress = {
  lineOne: 'navigate quantifying Italy',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
