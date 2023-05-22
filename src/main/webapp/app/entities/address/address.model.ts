export interface IAddress {
  id: number;
  lineOne?: string | null;
  lineTwo?: string | null;
  aptNo?: string | null;
  city?: string | null;
  state?: string | null;
  country?: string | null;
  pinCode?: string | null;
}

export type NewAddress = Omit<IAddress, 'id'> & { id: null };
