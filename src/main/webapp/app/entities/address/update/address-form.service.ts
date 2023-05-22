import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAddress, NewAddress } from '../address.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAddress for edit and NewAddressFormGroupInput for create.
 */
type AddressFormGroupInput = IAddress | PartialWithRequiredKeyOf<NewAddress>;

type AddressFormDefaults = Pick<NewAddress, 'id'>;

type AddressFormGroupContent = {
  id: FormControl<IAddress['id'] | NewAddress['id']>;
  lineOne: FormControl<IAddress['lineOne']>;
  lineTwo: FormControl<IAddress['lineTwo']>;
  aptNo: FormControl<IAddress['aptNo']>;
  city: FormControl<IAddress['city']>;
  state: FormControl<IAddress['state']>;
  country: FormControl<IAddress['country']>;
  pinCode: FormControl<IAddress['pinCode']>;
};

export type AddressFormGroup = FormGroup<AddressFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AddressFormService {
  createAddressFormGroup(address: AddressFormGroupInput = { id: null }): AddressFormGroup {
    const addressRawValue = {
      ...this.getFormDefaults(),
      ...address,
    };
    return new FormGroup<AddressFormGroupContent>({
      id: new FormControl(
        { value: addressRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      lineOne: new FormControl(addressRawValue.lineOne, {
        validators: [Validators.required],
      }),
      lineTwo: new FormControl(addressRawValue.lineTwo),
      aptNo: new FormControl(addressRawValue.aptNo),
      city: new FormControl(addressRawValue.city),
      state: new FormControl(addressRawValue.state),
      country: new FormControl(addressRawValue.country),
      pinCode: new FormControl(addressRawValue.pinCode, {
        validators: [Validators.pattern('^[1-9]0{4}$')],
      }),
    });
  }

  getAddress(form: AddressFormGroup): IAddress | NewAddress {
    return form.getRawValue() as IAddress | NewAddress;
  }

  resetForm(form: AddressFormGroup, address: AddressFormGroupInput): void {
    const addressRawValue = { ...this.getFormDefaults(), ...address };
    form.reset(
      {
        ...addressRawValue,
        id: { value: addressRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AddressFormDefaults {
    return {
      id: null,
    };
  }
}
