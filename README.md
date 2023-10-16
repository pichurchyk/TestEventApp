# EventsApp

Requirements:
https://docs.google.com/document/d/136b0si_J-QH8YejF3qIyXeVQidPrsqw4/edit?usp=sharing&ouid=117204377689453756386&rtpof=true&sd=true

## Application Tech Stack
- Kotlin
- XML
- Koin
- Ktor
- Coroutines
- MVVM
- Clean Arch
- Mockk
- Kotest

## Decomposition & Estimation

**Total estimation = ~17 hours**

1. **General** - ~3 hours
	-  Setting up project (libraries import, setting up dimens, colors, fonts, icon imports ets.)
	- Implementation of base fragment, ui extensions
	- Setting up DI
2. **Events Screen** - ~4 hours
	- Domain layer implementation (`useCases, repository, entities`)
	- Presentation layer implementation (`layout, recycler view adapter, viewModel`)
	- Data layer implementation (r`epository, remoteDataSource`) **paging doesn't supported by API**
	- Unit tests
3. **Schedule** - ~6 hours
	- Domain layer implementation (`useCases, repository, entities`)
	- Presentation layer implementation (`layout, recycler view adapter, viewModel, auto refresh`)
	- Data layer implementation (`repository, remoteDataSource`) **paging doesn't supported by API**
	- Unit tests
4. **Playback screen** - ~4 hours

> **Note:** All points listed above will be completed in order and applied step by step
