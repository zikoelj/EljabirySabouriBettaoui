import { Field, useField } from 'formik'
import React from 'react'

export default function CheckBoxFields({...props}) {

    const [field, meta] = useField(props)

  return (
    
    <Field {...field} {...props} />
  )
}
