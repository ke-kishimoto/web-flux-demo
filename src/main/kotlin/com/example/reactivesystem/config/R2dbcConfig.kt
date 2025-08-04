package com.example.reactivesystem.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories(
    basePackages = ["com.example.reactivesystem.customer"]
)
class R2dbcConfig {
    // R2DBCの設定はapplication.propertiesで自動設定されるため、
    // ここでは明示的にリポジトリスキャンのみ有効化
}
