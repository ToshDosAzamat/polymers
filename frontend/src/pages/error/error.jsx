import React from 'react'
import { useTranslation } from 'react-i18next';

const Error = () => {
  const { t, i18n } = useTranslation();
  return (
    <div>
      <div class="d-flex flex-column min-vh-100 justify-content-center align-items-center bg-light p-5">
        <div class="text-center">
          <h1 class="display-1 fw-bold">404</h1>
          <p class="fs-3">
            <span class="text-danger">{t('error_sorry')}</span>
            <span>{t('error_about')}</span>
          </p>
          <p class="lead">{t('error_text')}</p>
          <a href="/" class="btn btn-primary">{t('error_back')}</a>
        </div>
      </div>
    </div>
  )
}

export default Error